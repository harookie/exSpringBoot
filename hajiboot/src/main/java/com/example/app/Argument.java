package com.example.app;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * <ul>
 * <li>업무 그룹명 : tango-inventory-dr-controller-daemon</li>
 * <li>서브 업무명 : com.example.app</li>
 * <li>설  명 : Argument</li>
 * <li>작성일 : 2017. 11. 17.</li>
 * <li>작성자 : 하종현(Mobigen co.)</li>
 * </ul>
 */
@Data
@RequiredArgsConstructor
public class Argument {
    private final int a;
    private final int b;
}
