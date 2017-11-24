package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <ul>
 * <li>업무 그룹명 : tango-inventory-dr-controller-daemon</li>
 * <li>서브 업무명 : example.domain</li>
 * <li>설  명 : Customer</li>
 * <li>작성일 : 2017. 11. 17.</li>
 * <li>작성자 : 하종현(Mobigen co.)</li>
 * </ul>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Integer id;
    private String firstName;
    private String lastName;
}
