package com.assignment.diet.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.diet.entity.DietUser;
import com.assignment.diet.response.vo.DietUserResponseVO;
import com.assignment.diet.vo.Role;


@Repository
public interface DietUserDao extends JpaRepository<DietUser, Long> {

	String DIET_USER_JOIN_BATCH = "select referral_Id || ',' || full_Name || ',' || age || ',' || gender || ',' || mobile || ',' || role || ',' || temp.batch_id || ',' || nvl(b.name,'') || ',' || email "
			+ "from ( "
			+ " select du.referral_Id, full_Name, age, "
			+ "nvl(gender, '') as gender, nvl(mobile, '') as mobile, role,"
			+ " nvl((case when bm.batch_id is not null then bm.batch_id else ch.batch end), 0) as batch_id,"
			+ " nvl(email, '') as email"
			+ " from diet_user du"
			+ " left outer join"
			+ " batch_motivator bm on du.referral_Id = bm.motivator_Id"
			+ " left outer join challenger ch on du.referral_Id = ch.referral_Id"
			+ " ) temp"
			+ " left outer join batch b on"
			+ " temp.batch_id = b.batch_id"
			+ " WHERE role <> 'ADMIN'";
	
	String SINGLE_DIET_USER_JOIN_BATCH = DIET_USER_JOIN_BATCH + " AND referral_Id = :referralId";

	String ALL_MOTIVATORS_OF_GIVEN_BATCH = "SELECT new com.assignment.diet.response.vo.DietUserResponseVO(referralId, fullName, age, gender, mobile,"
			+ "email) FROM DietUser WHERE referralId IN(:referralIds)";
	
	public DietUser findByEmail(String email);

	public DietUser findByUserName(String username);

	@Query(ALL_MOTIVATORS_OF_GIVEN_BATCH)
	public List<DietUserResponseVO> findByReferralIdIn(@Param("referralIds") List<Long> referralIds);

	public List<DietUser> findByRoleNot(Role admin);

	@Query(value = DIET_USER_JOIN_BATCH, nativeQuery = true)
	List<String> getAllDietUserWithBatch();
	
	@Query(value = SINGLE_DIET_USER_JOIN_BATCH, nativeQuery = true)
	Optional<String> getSingleDietUserWithBatch(@Param("referralId") Long referralId);
}
