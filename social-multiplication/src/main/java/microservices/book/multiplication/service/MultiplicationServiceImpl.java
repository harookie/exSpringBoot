package microservices.book.multiplication.service;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.book.multiplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
class MultiplicationServiceImpl implements MultiplicationService {
    private RandomGeneratorService randomGeneratorService;

    @Autowired
    public MultiplicationServiceImpl(RandomGeneratorService randomGeneratorService,
                                     MultiplicationResultAttemptRepository attemptRepository,
                                     UserRepository userRepository) {
        this.randomGeneratorService = randomGeneratorService;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();

        return new Multiplication(factorA, factorB);
    }

    @Override
    public boolean checkAttempt(MultiplicationResultAttempt attempt) {
        boolean correct = attempt.getResultAttempt() ==
                attempt.getMultiplication().getFactorA() *
                        attempt.getMultiplication().getFactorB();

        Assert.isTrue(!attempt.isCorrect());

        MultiplicationResultAttempt checkAttempt =
                new MultiplicationResultAttempt(attempt.getUser(),
                        attempt.getMultiplication(),
                        attempt.getResultAttempt(),
                        correct);

        return correct;
    }
}