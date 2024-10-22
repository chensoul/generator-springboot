package <%= packageName %>.repositories;

import <%= packageName %>.entities.<%= entityName %>;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface <%= entityName %>Repository extends JpaRepository<<%= entityName %>, Long> {}
