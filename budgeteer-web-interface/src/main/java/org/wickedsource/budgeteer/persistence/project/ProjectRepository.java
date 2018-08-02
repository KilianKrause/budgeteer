package org.wickedsource.budgeteer.persistence.project;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.wickedsource.budgeteer.persistence.budget.BudgetEntity;

import java.util.Optional;

public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {

    @Query("select pcf from ProjectContractField pcf where pcf.project.id = :projectId AND pcf.fieldName = :fieldName")
    public ProjectContractField findContractFieldByName(@Param("projectId") long projectId, @Param("fieldName") String fieldName);

    @Query("select p from ProjectEntity p join fetch p.contractFields where p.id = :id ")
    public ProjectEntity findByContractFieldsId(@Param("id") long id);

    default ProjectEntity findOne(long projectId){
        Optional<ProjectEntity> result = findById(projectId);
        return result.orElse(null);
    }
}
