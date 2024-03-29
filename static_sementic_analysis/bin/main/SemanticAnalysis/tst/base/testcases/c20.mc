// Euclid's algorithm to compute the greatest
// common divisor of two integers:

int gcd(int a, int b)
{
     while (b != 0) {
        if (a > b) {
             a = a - b;
        } else {
             b = b - a;
        }
     }
     return a;
}

int main ()
{
    gcd(22,12);
    return 0;
}
