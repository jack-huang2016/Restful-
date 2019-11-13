/**
 * FileName: UserController
 * Author:   huang.yj
 * Date:     2019/11/12 18:18
 * Description: 用户控制器
 */
package com.sample.test.web.controller;

import com.sample.test.common.entity.User;
import com.sample.test.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 〈用户控制器〉
 *
 * @author huang.yj
 * @create 2019/11/12
 * @since 0.0.1
 */
@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * GET请求 -- 查询操作
     * @param id
     * @return
     */
    // @RequestMapping(value = "{userId}" , method = RequestMethod.GET)
    @GetMapping(value = "{userId}")  // 等价于以上的注解
    public ResponseEntity<User> queryUserById(@PathVariable("userId") int id){
        try {
            User user = userService.queryUserById(id);
            if(user == null){
                // 资源不存在，响应404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            // 成功，响应200
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * POST请求 -- 保存操作
     * @param user
     * @return
     */
     // @RequestMapping(method = RequestMethod.POST)
    @PostMapping // 等价于以上的注解
    public ResponseEntity<Void> saveUser(User user){
        try {
            userService.saveUser(user);
            // 响应201
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            // 出错，响应500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * PUT请求 -- 修改操作
     * @param user
     * @return
     */
     // @RequestMapping(method = RequestMethod.PUT)
    @PutMapping  // 等价于以上的注解
    public ResponseEntity<Void> updateUser(User user){
        try {
            Integer count = userService.updateUser(user);
            if (count.intValue() == 1) {
                // 响应204
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * DELETE请求 -- 删除操作
     * @param id
     * @return
     */
    // @RequestMapping(method = RequestMethod.DELETE)
    @DeleteMapping  // 等价于以上的注解
    public ResponseEntity<Void> deleteUserById(@RequestParam(value = "id", defaultValue = "0") int id){
        try {
            if(id == 0){
                // 参数不合法，响应400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            userService.deleteUserById(id);
            // 成功，响应204
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}