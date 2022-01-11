# Example test suite for demoblaze.com
### How to run tests
Use Maven `test` target:

```bash
>mvn test
```

### Test configuration
You can pass custom API endpoint using `url` system property:

```bash
>mvn test -Durl=http://localhost:8080
```

### Choosing which test to run
Tests are split in two groups:
- `add` - test adding products to a cart
- `remove` - test removing a product from a cart.

To choose the group to run, simply pass the `groups` system property to Maven:

```bash
>mvn test -Dgroups=add
```

Have a nice day.