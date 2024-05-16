package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.MyUser;

public interface MyUserRepo extends CrudRepository<MyUser, Integer>{

}
