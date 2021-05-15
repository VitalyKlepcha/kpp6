package com.example.test1.controller;

import com.example.test1.exceptions.InputDataException;
import com.example.test1.models.Post;
import com.example.test1.repository.PostRepository;
import com.example.test1.service.FileWorker;
import com.example.test1.parametres.entityParametres;
import com.example.test1.service.ListWorkerService;
import com.example.test1.service.ReverseService;
import com.example.test1.service.SortListService;
import com.example.test1.threads.ErrorsOutputRunner;
import com.example.test1.threads.ListOutputRunner;
import com.example.test1.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.sql.*;
import javax.persistence.*;



@Controller
public class MainController {
    private static final String DATABASE = "D:\\test1\\database.txt";
    private static final String ERRORDATABASE = "D:\\test1\\errorDatabase.txt";
    private FileWorker fileWorker;
    private SortListService sortListService;
    private entityParametres entityParametres;
    private ArrayList<entityParametres> list;
    private ListWorkerService listWorkerService;
    private PostRepository postRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public MainController(
            FileWorker fileWorker,
            SortListService sortListService,
            ListWorkerService listWorkerService,
            PostRepository postRepository
    ) {
        this.fileWorker = fileWorker;
        this.sortListService = sortListService;
        this.listWorkerService = listWorkerService;
        this.postRepository = postRepository;
    }

    @GetMapping({"/", "/main"})
    public String greeting(Model model) {
        model.addAttribute("title","Главная страница");
        return "home";
    }

    @PostMapping("/main")
    public String postGreeting(
            @RequestParam(required = false) String text1,
            @RequestParam(required = false) String text2,
            Model model
    ) {
        list = new ArrayList<>();
        try{
            Post post = new Post();
            entityParametres = new entityParametres(text1, text2);
            entityParametres.Reverse();
            post.setFstring(entityParametres.getFirstLine());
            post.setSstring(entityParametres.getSecondLine());
            postRepository.save(post);
            //entityManager.persist(post);

            //listWorkerService.AddObject(list, entityParametres);
//            Runnable ListOutputRunner2 = () -> {
//                for (entityParametres el : list) {
//                    System.out.println(el.getFirstLine() + el.getSecondLine());
//                }
//            };
//
//            Thread listOutputThread = new Thread(ListOutputRunner2);
//            listOutputThread.start();
//            String url = "jdbc:mysql://localhost:3306/test_db?useUnicode=true&serverTimezone=UTC";
//            String username = "root";
//            String password = "3366";
//            Connection conn = DriverManager.getConnection(url, username, password);
//            String sql = "INSERT INTO test_table2 (fstring, sstring) Values (?, ?)";
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//            for(int num = 0; num < 3; num++) {
//                preparedStatement.setString(1, "frombatchf" + num);
//                preparedStatement.setString(2, "second" + num);
//                preparedStatement.addBatch();
//            }
//            preparedStatement.executeBatch();
        }
        catch(Exception ex){
            model.addAttribute("error", ex.getMessage());
            fileWorker.write(ERRORDATABASE, ex.getMessage());
            Thread errorOutputThread = new Thread(new ErrorsOutputRunner(fileWorker));
            errorOutputThread.start();
        }

        model.addAttribute("leftInput", entityParametres.getFirstLine());
        model.addAttribute("rightInput", entityParametres.getSecondLine());
        return "home";
    }
    @PostMapping("/database")
    public String database(Model model){
        model.addAttribute("data", fileWorker.read(DATABASE));
        return "database";
    }
}

