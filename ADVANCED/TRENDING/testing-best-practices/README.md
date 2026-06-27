# testing-best-practices

JUnit 5 + Mockito test suite demonstrating professional testing practices.

## What It Teaches

### JUnit 5
- `@Test`, `@BeforeEach`, `@AfterEach`
- `@DisplayName` — human-readable test names
- `@ParameterizedTest` + `@ValueSource` — run one test with multiple inputs
- `assertEquals`, `assertTrue`, `assertFalse`, `assertThrows`

### Mockito
- `@Mock` — create a fake version of a dependency
- `@InjectMocks` — inject mocks into the class under test
- `when(...).thenReturn(...)` — stub behavior
- `verify(mock, times(n)).method(...)` — assert interactions
- `verifyNoInteractions(mock)` — assert nothing was called
- `ArgumentCaptor` — capture and inspect arguments passed to mocks

### Testing Principles Demonstrated
- **Arrange-Act-Assert** pattern in every test
- Unit tests should NOT call real databases or send real emails — use mocks
- Test happy path, error cases, and edge cases separately
- One assertion per test (mostly) for clarity

## Run
```
mvn test
```

## Project Structure
```
src/
├── main/java/.../
│   ├── User.java             model
│   ├── UserRepository.java   data access interface (mocked in tests)
│   ├── EmailService.java     email interface (mocked in tests)
│   └── UserService.java      ← the class under test (business logic)
└── test/java/.../
    └── UserServiceTest.java  ← 12 tests covering all scenarios
```
