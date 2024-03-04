## Bitwise XOR
XOR is a logical bitwise operator that returns `0` (false) if both bits are the same and returns `1` (true) otherwise.
In other words, it only returns `1` if exactly one bit is set to `1` out of the two bits in comparison.

| A | B | A xor B |
|---|---|:-------:|
| 0 | 0 |    0    |
| 0 | 1 |    1    |
| 1 | 0 |    1    |
| 1 | 1 |    0    |

#### Important properties of XOR to remember

Following are some important properties of XOR to remember:
<ul>
    <li> Taking XOR of a number with itself returns 0, e.g.,
    <ul>
        <li> 1 ^ 1 = 0
        <li> 29 ^ 29 = 0
    </ul>
    <li> Taking XOR of a number with 0 returns the same number, e.g.,
    <ul>
        <li> 1 ^ 0 = 1
        <li> 31 ^ 0 = 31
    </ul>
    <li> XOR is Associative & Commutative, which means:
    <ul>
        <li> (a ^ b) ^ c = a ^ (b ^ c)
        <li> a ^ b = b ^ a
    </ul>
</ul>