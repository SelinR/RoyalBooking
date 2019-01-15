package controllers;

import entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.RoomService;

@Controller
public class RoomController {
    private RoomService roomService;

    @RequestMapping(value = "rooms", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("allRooms", roomService.getAll());
        return "rooms";
    }

    @RequestMapping(value = "rooms", method = RequestMethod.POST)
    public String save(@ModelAttribute("room") Room room) {
        if(room.getId() == 0) {
            roomService.save(room);
        } else {
            roomService.update(room);
        }
        return "redirect:/rooms";
    }

    @RequestMapping(value= "/rooms/delete/{id}")
    public String remove(@PathVariable("id") int id) {
        roomService.delete(id);
        return "redirect:/rooms";
    }

    @RequestMapping(value = "/rooms/edit/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", roomService.getById(id));
        model.addAttribute("listUsers", roomService.getAll());
        return "/rooms";
    }

    @RequestMapping("roomdata/{id}")
    public String getById(@PathVariable("id") int id, Model model){
        model.addAttribute("book", this.roomService.getById(id));
        return "roomdata";
    }


    @Autowired
    @Qualifier(value = "roomService")
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
