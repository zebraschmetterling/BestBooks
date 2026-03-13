package service;

import entity.Friends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.FriendsRepository;

import java.util.List;

@Service
public class FriendsService {

    @Autowired
    private FriendsRepository friendsRepository;

    public Friends save(Friends newFriends) {
        return friendsRepository.save(newFriends);
    }

    public List<Friends> findAll() {
        return friendsRepository.findAll();
    }

    public Friends findById(int friendsId) {
        return friendsRepository.findById(friendsId).orElse(null);
    }

    public void deleteById(int friendsId) {
        try {
            friendsRepository.deleteById(friendsId);
        } catch (Exception e) {
            System.err.println("Unable to delete Friends with ID: " + friendsId);
        }
    }

}
