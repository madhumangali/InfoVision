package com.infovision.canteen.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.admin.MenuItem;
import com.infovision.canteen.model.feedback.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, UUID>{

	@Query("select s from Feedback s where s.website.websiteName=:string")
	List<Feedback> findAllByName(String string);

}
