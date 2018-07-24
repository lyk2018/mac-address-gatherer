package tr.org.linux.kamp.macaddressgatherer;

import org.springframework.data.repository.CrudRepository;

interface UserRepository extends CrudRepository<User, Long> {
}
