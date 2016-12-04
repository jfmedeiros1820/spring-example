package org.codehouse.store.daos;

import org.codehouse.store.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserDAO implements UserDetailsService {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    List<User> users = entityManager.createQuery("select u from User u where u.username = :username", User.class)
        .setParameter("username", username)
        .getResultList();

    if (users.isEmpty()) {
      throw new UsernameNotFoundException("The user" + username + " was not found");
    }

    return users.get(0);
  }
}
