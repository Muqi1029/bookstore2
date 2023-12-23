import pytest

import uuid

from fe.access.new_buyer import register_new_buyer
from fe.access.new_seller import register_new_seller


class TestSearchBook:

    @pytest.fixture(autouse=True)
    def pre_run_initialization(self):
        self.buyer_id = "test_new_search_buyer_id_{}".format(str(uuid.uuid1()))
        self.password = "test_new_search_buyer_id_{}".format(str(uuid.uuid1()))
        self.buyer = register_new_buyer(self.buyer_id, self.password)

        self.user_id = "test_crete_store_user_{}".format(str(uuid.uuid1()))
        self.store_id = "test_create_store_store_{}".format(str(uuid.uuid1()))
        self.password = self.user_id
        self.seller = register_new_seller(self.user_id, self.password)
        code = self.seller.create_store(self.store_id)
        assert code == 200

        self.keyword = "hello"

    def test_all_field_search(self):
        code, content = self.buyer.search(self.keyword)
        assert code == 200

    def test_pagination(self):
        code, content = self.buyer.search(self.keyword, page=2)
        assert code == 200

    def test_specific_store(self):
        code, content = self.buyer.search(self.keyword,
                                          store_id=self.store_id)
        assert code == 200
