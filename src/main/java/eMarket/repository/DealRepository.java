package eMarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eMarket.domain.Deal;

public interface DealRepository extends CrudRepository<Deal, Integer> {
    Deal findById(int id);
    @Query(value = "SELECT deal_id,deal_start_date,deal_end_date,deal_discount, deal_product from product as p, deal as d where d.deal_product=p.product_id and d.deal_id=?1", nativeQuery=true)
    List<Deal> findAllByCustomQuery(int id);
}