package br.edu.iff.ccc.bsi.sgvet.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.sgvet.entities.Tasks;
import br.edu.iff.ccc.bsi.sgvet.repository.TasksRepository;

@Service
public class TasksService {

	@Autowired
	private TasksRepository TaskRep;
	
	public Optional<Tasks> findById(Long id) {
		Optional<Tasks> task = Optional.ofNullable(TaskRep.findById(id).orElseThrow(null));
		return task;
	}
}
