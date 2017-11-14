package hello.service;

import hello.data.repo.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackSerivice {
    @Autowired
    private TrackRepository trackRepository;

}
