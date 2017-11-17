package com.example.app;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Scanner;

/**
 * <ul>
 * <li>업무 그룹명 : tango-inventory-dr-controller-daemon</li>
 * <li>서브 업무명 : com.example.app</li>
 * <li>설  명 : ScannerArgumentResolver</li>
 * <li>작성일 : 2017. 11. 17.</li>
 * <li>작성자 : 하종현(Mobigen co.)</li>
 * </ul>
 */
@Component
public class ScannerArgumentResolver implements ArgumentResolver {
    @Override
    public Argument resolve(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        return new Argument(a, b);
    }
}
