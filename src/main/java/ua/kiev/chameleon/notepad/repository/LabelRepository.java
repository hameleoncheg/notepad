package ua.kiev.chameleon.notepad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kiev.chameleon.notepad.entity.Label;

import java.util.List;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
    Label findByNameAndUser_Id(String name,long userId);
    List<Label> findAllByUser_Id(Long id);
}

