package eMarket.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eMarket.EMarketApp;
import eMarket.repository.UserInfoRepository;

@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserInfoRepository userRepo;

	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {

		eMarket.domain.UserInfo domainUser = userRepo.findByLogin(login);

		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		return new User(
				domainUser.getLogin(),
				domainUser.getPassword(),
				enabled,
				accountNonExpired,
				credentialsNonExpired,
				accountNonLocked,
				getAuthorities(domainUser.getRole().getId())
		);
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}

	private List<String> getRoles(Integer role) {

		List<String> roles = new ArrayList<String>();

		switch (role.intValue()) {
		case EMarketApp.ADMIN:
			roles.add("ROLE_ADMIN");
			break;
		case EMarketApp.USER:
			roles.add("ROLE_USER");
			break;
		case EMarketApp.PREMIUM:
			roles.add("ROLE_PREMIUM");
			break;
		}
		return roles;
	}

	private static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
}
