package com.daw.ticketsdaw.Controllers;

import com.daw.ticketsdaw.Entities.Evento;
import com.daw.ticketsdaw.Services.EventosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    EventosService eventosService;

    @GetMapping({"/", ""})
    public String show(ModelMap modelMap){
        modelMap.addAttribute("eventos", eventosService.read());
        return "eventos/show";
    }

    @GetMapping({"/{id}"})
    public String showOne(ModelMap modelMap, @PathVariable(name="id") Integer eventoId){
        Evento evento = eventosService.read(eventoId);
        List<Evento> eventoList = new ArrayList<>();
        eventoList.add(evento);
        modelMap.addAttribute("eventos", eventoList);
        return "eventos/show";
    }

    @GetMapping({"crear"})
    public String showForm(ModelMap model){
        model.addAttribute("evento", new Evento());
        model.addAttribute("action", "crear");
        return "eventos/create";
    }

    @GetMapping({"actualizar", "actualizar/{id}"})
    public String showUpdateForm(ModelMap model, @PathVariable(name="id", required = false) Integer eventoId){
        Evento evento;
        if(eventoId != null){
            evento = eventosService.read(eventoId);
        }
        else{
            evento = new Evento();
        }
        model.addAttribute("evento", evento);
        model.addAttribute("action", "actualizar");
        return "eventos/create";
    }

    @PostMapping({"crear"})
    public String insertEvento(@ModelAttribute Evento evento){
        eventosService.create(evento);
        return "redirect:/eventos";
    }

    @PostMapping({"actualizar"})
    public String updateEvento(@ModelAttribute Evento evento){
        eventosService.update(evento);
        return "redirect:/eventos";
    }

    @GetMapping({"borrar"})
    public String deleteEvento(ModelMap map, @RequestParam String id){
        Evento evento = new Evento();
        evento.setId(Integer.parseInt(id));
        eventosService.remove(evento);
        return "redirect:/eventos";
    }
}
