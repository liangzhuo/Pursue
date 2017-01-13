package com.liang.repository;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import com.liang.model.EmailAddress;
import com.liang.model.User;
import com.liang.model.ZipCode;

/**
 * 方法名如：find…By, read…By, query…By, count…By, and get…By
 * @Repository,@CrudRepository,@PagingAndSortingRepository
 * @author liangz
 *
 */
public interface UserRepository extends PagingAndSortingRepository<User, String> {
	List<User> findByLastName(String lastName);
	
	User findFirstByLastName(String lastName);
	
	
	/*
	List<User> findByEmailAddressAndLastname(EmailAddress emailAddress, String lastName);

	// Enables the distinct flag for the query
	List<User> findDistinctPeopleByLastnameOrFirstname(String lastName, String firstName);
	List<User> findPeopleDistinctByLastnameOrFirstname(String lastName, String firstName);

	// Enabling ignoring case for an individual property
	List<User> findByLastnameIgnoreCase(String lastName);
	// Enabling ignoring case for all suitable properties
	List<User> findByLastnameAndFirstnameAllIgnoreCase(String lastName, String firstName);

	// Enabling static ORDER BY for a query
	List<User> findByLastnameOrderByFirstnameAsc(String lastName);
	List<User> findByLastnameOrderByFirstnameDesc(String lastName);
	
	List<User> findByAddressZipCode(ZipCode zipCode);
	// 推荐使用该方法
	List<User> findByAddress_ZipCode(ZipCode zipCode);
	
	
	Page<User> findByLastname(String lastName, Pageable pageable);

	//推荐使用该方法，在大数据量的结果集中更高效
	//Slice<User> findByLastname(String lastName, Pageable pageable);

	List<User> findByLastname(String lastName, Sort sort);

	//List<User> findByLastname(String lastName, Pageable pageable);
	
	//Limiting query results
	User findFirstByOrderByLastnameAsc();

	User findTopByOrderByAgeDesc();

	Page<User> queryFirst10ByLastname(String lastName, Pageable pageable);

	Slice<User> findTop3ByLastname(String lastName, Pageable pageable);

	List<User> findFirst10ByLastname(String lastName, Sort sort);

	List<User> findTop10ByLastname(String lastName, Pageable pageable);
	
	@Async
	Future<User> findByFirstname(String firstName); 
	
	@Async
	ListenableFuture<User> findOneByLastname(String lastName);  
	*/
}
