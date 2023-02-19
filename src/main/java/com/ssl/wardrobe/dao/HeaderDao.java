package com.ssl.wardrobe.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ssl.wardrobe.model.HeaderModel;


public interface HeaderDao extends PagingAndSortingRepository<HeaderModel, Long> {

	@Query("Select count(h) From HeaderModel h Where h.memberId=?1 and (bitDate BETWEEN ?2 AND ?3 )")
	public int getTotalCountOfTranactions(String memberId, Timestamp fromDate, Timestamp toDate);
	
	@Query("Select count(h) From HeaderModel h Where h.memberId=?1")
	public int getTotalCountOfTranactions(String memberId);

	@Query(value = "Select h From HeaderModel h Where h.memberId=?1 and (h.bitDate BETWEEN ?2 AND ?3 ) ORDER BY h.bitDate DESC")
	public List<HeaderModel> findMemberTransactionsByMemberId(String memberId, Timestamp fromDate, Timestamp toDate,
			Pageable pageable);

	@Query("Select h From HeaderModel h Where h.memberId=?1 and h.bitType in (?2) and (h.bitDate BETWEEN ?3 AND ?4 ) ORDER BY h.bitDate DESC")
	public List<HeaderModel> findMemberTransactionsByMemberId(String memberId, List<String> salesattribute,
			Timestamp fromDate, Timestamp toDate, Pageable pageable);

	@Query(value = "Select h From HeaderModel h Where h.memberId=?1 ORDER BY h.bitDate DESC")
	public List<HeaderModel> findMemberTransactionsByMemberId(String memberId, Pageable pageable);

	@Query("Select h From HeaderModel h Where h.memberId=?1 and h.bitType in (?2) ORDER BY h.bitDate DESC")
	public List<HeaderModel> findMemberTransactionsByMemberId(String memberId, List<String> returnattributes,
			Pageable pageable);
	
	@Query(value = "Select h From HeaderModel h Where h.bitReference=?1")
	public HeaderModel findMemberTransactionsByBitReference(String bitreference);

}
