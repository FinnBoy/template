package finn.sample.ds.service;

import finn.sample.ds.entity.Role;
import finn.sample.ds.entity.User;
import finn.sample.ds.repository.RoleRepository;
import finn.sample.ds.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Finn Zhao
 * @version 2019-09-29
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public void execute() {
        List<Role> roleList = this.roleRepository.findAll();
        List<User> userList = this.userRepository.findAll();
    }

    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
