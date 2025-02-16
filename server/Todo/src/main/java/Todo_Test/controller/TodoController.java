package Todo_Test.controller;

import Todo_Test.pojo.Todo;
import Todo_Test.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;

    @RequestMapping("/showTodos")
    @ResponseBody
    public List<Todo> showTodos() {
        List list = todoService.showAllTodos();
        return list;
    }

    @GetMapping("/show")
    @ResponseBody
    public Todo showTodoById(int id) {
        return todoService.showOneTodo(id);
    }

    @PostMapping("/save")
    @ResponseBody
    public String save(Todo todo) {
        int i=  todoService.addTodo(todo);
        if (i>0) return "success";
        else return "fail";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Todo> searchTodos(String keyword) {
        return todoService.searchTodos(keyword);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public String delete(int id) {
         int t=todoService.deleteTodo(id);
         if (t>0) return "success";
         else return "fail";
    }

    @PutMapping("/update")
    @ResponseBody
    public String update(Todo todo) {
        int result = todoService.updateTodo(todo);
        if (result > 0) {
            return "success";
        } else {
            return "fail";
        }
    }
}
