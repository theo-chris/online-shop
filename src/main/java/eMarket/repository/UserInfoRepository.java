package eMarket.repository;

import org.springframework.data.repository.CrudRepository;

import eMarket.domain.UserInfo;

public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {
	UserInfo findById(int id);
    UserInfo findByLogin(String login);
}