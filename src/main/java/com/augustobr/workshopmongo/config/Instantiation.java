package com.augustobr.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.augustobr.workshopmongo.domain.Post;
import com.augustobr.workshopmongo.domain.User;
import com.augustobr.workshopmongo.dto.AuthorDTO;
import com.augustobr.workshopmongo.dto.CommentDTO;
import com.augustobr.workshopmongo.repository.PostRepository;
import com.augustobr.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {		
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("2018/03/2023"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPost().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
				
		post1.getComment().add(new CommentDTO("Boa viagem mano", sdf.parse("2018/03/21"), new AuthorDTO(alex)));
		post1.getComment().add(new CommentDTO("Aproveite", sdf.parse("2018/03/22"), new AuthorDTO(bob)));
		
		post2.getComment().add(new CommentDTO("Tenha um ótimo dia", sdf.parse("2018/03/23"), new AuthorDTO(alex)));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
	}

	
}
