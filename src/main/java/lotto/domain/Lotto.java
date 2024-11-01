package lotto.domain;

import java.util.List;
import java.util.function.Predicate;
import lotto.constants.ErrorMessages;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public boolean hasNumber(int number) {
        return numbers.contains(number);
    }

    public int countDuplication(Lotto lotto) {
        return numbers.stream()
                .filter(x -> lotto.getLotto().stream()
                        .anyMatch(Predicate.isEqual(x)))
                .toList().size();
    }

    public List<Integer> getLotto() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        validateCount(numbers);
        validateDuplication(numbers);
    }

    private void validateCount(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_LOTTO_COUNT.getMessage());
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        if (isDuplicated(numbers)) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_LOTTO_DUPLICATED.getMessage());
        }
    }

    private boolean isDuplicated(List<Integer> numbers) {
        return numbers.size() != numbers.stream().distinct().count();
    }
}
