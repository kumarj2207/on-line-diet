package com.assignment.diet.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assignment.diet.entity.Batch;
import com.assignment.diet.response.vo.BatchResponseVO;

public interface BatchDao extends JpaRepository<Batch, Long> {

	String ALL_BATCH_WITH_PARENT_NAME = "select new com.assignment.diet.response.vo.BatchResponseVO(b1.batchId, b1.name, b2.batchId as parentBatchId, b2.name as parentBatchName) "
			+ "from Batch b1 left outer join Batch b2 on b1.parentBatchId = b2.batchId";
	
	String SINGLE_BATCH_WITH_PARENT_NAME = ALL_BATCH_WITH_PARENT_NAME + " WHERE b1.batchId = :id";

	@Query(value = ALL_BATCH_WITH_PARENT_NAME, nativeQuery = false)
	List<BatchResponseVO> getAllBatchWithParentName();
	
	@Query(value = SINGLE_BATCH_WITH_PARENT_NAME)
	List<BatchResponseVO> getSingleBatchWithParentName(@Param("id") Long id);

	List<Batch> findByParentBatchIdIsNull();

	List<Batch> findByParentBatchId(Long batchId);

	Optional<Batch> findByNameAndParentBatchId(String name, Long parentBatchId);
}
