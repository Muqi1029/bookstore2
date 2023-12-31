import uuid

import pytest

from fe.access.book import Book
from fe.access.new_buyer import register_new_buyer
from fe.test.gen_book_data import GenBook


class TestShipment:
    @pytest.fixture(autouse=True)
    def pre_run_initialization(self):
        # create a seller, then let it create a store
        self.seller_id = "test_shipment_seller_id_{}".format(str(uuid.uuid1()))
        self.store_id = "test_shipment_store_id_{}".format(str(uuid.uuid1()))
        # create a buyer
        self.buyer_id = "test_shipment_buyer_id_{}".format(str(uuid.uuid1()))
        self.password = self.buyer_id

        # generate books by seller and store
        gen_book = GenBook(self.seller_id, self.store_id)
        self.seller = gen_book.seller
        ok, buy_book_id_list = gen_book.gen(non_exist_book_id=False, low_stock_level=False, max_book_count=5)
        self.buy_book_info_list = gen_book.buy_book_info_list
        assert ok

        # create a buyer to create an order
        b = register_new_buyer(self.buyer_id, self.buyer_id)
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

    def test_ok(self):
        code = self.seller.shipment(self.buyer_id, self.order_id)
        assert code == 200

    def test_invalid_order_id(self):
        code = self.seller.shipment(self.buyer_id, self.order_id + "_x")
        assert code != 200

    def test_authorization_error(self):
        code = self.seller.shipment(self.buyer_id + "_x", self.order_id)

    def test_books_duplicate_shipment(self):
        code = self.seller.shipment(self.buyer_id, self.order_id)
        assert code == 200
        code = self.seller.shipment(self.buyer_id, self.order_id)
        assert code != 200
