# library-api

## POST sign-up
```https://localhost:443/api/v1/sign-up```

| Authorization | Bearer token |
|---------------|--------------|
| Token         | token        |

### Body raw(json)

```json
{
    "email": "test@gmail.com",
    "password": "12345"
}
```

## POST sign-in
```https://localhost:443/api/v1/sign-in```

### Body raw(json)

```json
{
    "email": "test@gmail.com",
    "password": "12345"
}
```

## GET current-user
```https://localhost:443/api/v1/u```

| Authorization | Bearer token |
|---------------|--------------|
| Token         | token        |

## GET get-book-by-id
```https://localhost:443/api/v1/books/1```

| Authorization | Bearer token |
|---------------|--------------|
| Token         | token        |

## POST add-book
```https://localhost:443/api/v1/books/add-book```

| Authorization | Bearer token |
|---------------|--------------|
| Token         | token        |

### Body raw(json)

```json
{
    "name": "test",
    "genre": "FANTASY"
}
```

## DELETE drop-book

```https://localhost:443/api/v1/books/drop-book/1```

| Authorization | Bearer token |
|---------------|--------------|
| Token         | token        |

## PATCH set-admin

```https://localhost:443/api/v1/u/set-role?email=test@gmail.com```

| Authorization | Bearer token |
|---------------|--------------|
| Token         | token        |

### Query Params

| email | test@gmail.com |
|-------|----------------|

## GET books-paginated-result

```https://localhost:443/api/v1/books?page=0&size=5```

| Authorization | Bearer token |
|---------------|--------------|
| Token         | token        |

### Query Params

| page | 0 |
|------|---|
| size | 5 |

## PATCH update-book

```https://localhost:443/api/v1/books/1```

| Authorization | Bearer token |
|---------------|--------------|
| Token         | token        |

### Body raw(json)

```json
{
    "name": "name",
    "genre": "GENRE"
}
```
