import uuid

import pytest

from fe.access.book import Book
from fe.access.new_buyer import register_new_buyer
from fe.test.gen_book_data import GenBook


class TestReceive:
    @pytest.fixture(autouse=True)
    def pre_run_initialization(self):
        self.seller_id = "test_receive_seller_id_{}".format(str(uuid.uuid1()))
        self.store_id = "test_receive_store_id_{}".format(str(uuid.uuid1()))
        self.buyer_id = "test_receive_buyer_id_{}".format(str(uuid.uuid1()))
        self.password = self.buyer_id

        gen_book = GenBook(self.seller_id, self.store_id)
        self.seller = gen_book.seller
        ok, buy_book_id_list = gen_book.gen(non_exist_book_id=False, low_stock_level=False, max_book_count=5
                                            )
        self.buy_book_info_list = gen_book.buy_book_info_list
        assert ok
        b = register_new_buyer(self.buyer_id, self.password)
        self.buyer = b

        self.total_price = 0
        for item in self.buy_book_info_list:
            book: Book = item[0]
            num = item[1]
            if book.price is None:
                continue
            else:
                self.total_price = self.total_price + book.price * num

        code = self.buyer.add_funds(self.total_price + 1000000)
        assert code == 200

        code, self.order_id = b.new_order(self.store_id, buy_book_id_list)
        assert code == 200

        code = self.buyer.payment(self.order_id)
        assert code == 200
        yield

    # 收货正常
    def test_ok(self):
        code = self.seller.shipment(self.buyer_id, self.order_id)
        assert code == 200
        code = self.buyer.receive(self.buyer_id, self.order_id)
        assert code == 200

    # 订单号 order_id 不存在
    def test_invalid_order_id(self):
        code = self.seller.shipment(self.buyer_id, self.order_id)
        assert code == 200
        code = self.buyer.receive(self.buyer_id, self.order_id + 'x')
        assert code != 200

    # 权限错误 buyer_id 与 user_id 不对应
    def test_authorization_error(self):
        code = self.seller.shipment(self.buyer_id, self.order_id)
        assert code == 200
        code = self.buyer.receive(self.buyer_id + 'x', self.order_id)
        assert code != 200

    # 订单未发货
    def test_books_not_send(self):
        code = self.buyer.receive(self.buyer_id, self.order_id)
        assert code != 200

    # 订单已收货不可重复收货
    def test_books_duplicate_receive(self):
        code = self.seller.shipment(self.buyer_id, self.order_id)
        assert code == 200
        code = self.buyer.receive(self.buyer_id, self.order_id)
        assert code == 200
        code = self.buyer.receive(self.buyer_id, self.order_id)
        assert code != 200

