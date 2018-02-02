package microservices.book.multiplication.domain;

import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public final class Multiplication {
    private final int factorA;
    private final int factorB;
}
