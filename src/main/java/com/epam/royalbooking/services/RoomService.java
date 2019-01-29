package com.epam.royalbooking.services;

import com.epam.royalbooking.dao.RoomDao;
import com.epam.royalbooking.entities.Room;
import com.epam.royalbooking.enums.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private RoomDao dao;

    public List<Room> getAll() {
        return dao.findAll();
    }

    @Transactional
    public void save(Room room) {
        dao.save(room);
    }

    @Transactional
    public void update(Room room) {
        dao.save(room);
    }

    @Transactional
    public void delete(int id) {
        dao.deleteById(id);
    }

    public Room getById(int id) {
        return createRoom(id);
    }

    private Room createRoom(int id) {
        Optional<Room> room = dao.findById(id);
        if (room.isPresent()) {
            return room.get();
        } else {
            throw new RuntimeException("No room found.");
        }
    }

    public void imageSwitcher(RoomType roomType, Model model){
        switch (roomType ){
            case BASIC:
                model.addAttribute("imageUrl_1", "/media/basic1.jpg");
                model.addAttribute("imageUrl_2", "/media/basic2.jpg");
                model.addAttribute("imageUrl_3", "/media/basic3.jpg");
                break;
            case FAMILY:
                model.addAttribute("imageUrl_1", "/media/family1.jpg");
                model.addAttribute("imageUrl_2", "/media/family2.jpg");
                model.addAttribute("imageUrl_3", "/media/family3.jpg");
                break;
            case LUXURY:
                model.addAttribute("imageUrl_1", "/media/luxury1.jpg");
                model.addAttribute("imageUrl_2", "/media/luxury2.jpg");
                model.addAttribute("imageUrl_3", "/media/luxury3.jpg");
                break;
            case PENTHOUSE:
                model.addAttribute("imageUrl_1", "/media/pent1.jpg");
                model.addAttribute("imageUrl_2", "/media/pent2.jpg");
                model.addAttribute("imageUrl_3", "/media/pent3.jpg");
                break;
        }
    }

    @Autowired
    public void setDaoData(RoomDao dao) {
        this.dao = dao;
    }
}