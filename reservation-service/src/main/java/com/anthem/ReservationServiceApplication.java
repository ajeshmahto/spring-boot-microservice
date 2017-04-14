package com.anthem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Stream;

@SpringBootApplication
public class ReservationServiceApplication {

    @Autowired
    ReservationRepository reservationRepository;

    @Bean
    CommandLineRunner commandLineRunner(ReservationRepository reservationRepository){
            return Strings -> {
                Stream.of("ajesh","ramesh","john","kon").forEach(n->reservationRepository.save(new Reservation(n)));
            };
    }


    public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}
}

@RepositoryRestResource
interface  ReservationRepository extends JpaRepository<Reservation,Long> {

    /*@RestResource(path="by-name")
	Collection<Reservation> findByReservationName(@Param("rn") String rn);*/


}
@RefreshScope
@RestController
class MessageRestController{
    @Value("${message}")
    private String msg;
    @RequestMapping("/message")
    String message(){
        return this.msg;
    }
}




@Entity
class Reservation {

    @Id @GeneratedValue
	private Long id;
    @Column
	private String reservation;

    public Reservation(){

    }

	public Reservation( String reservation) {

		this.reservation = reservation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReservation() {
		return reservation;
	}

	public void setReservation(String reservation) {
		this.reservation = reservation;
	}

	@Override
	public String toString() {
		return "Reservation{" +
				"id=" + id +
				", reservation='" + reservation + '\'' +
				'}';
	}
}