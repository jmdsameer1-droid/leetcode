#include <stdbool.h>
#include <ctype.h>
#include <string.h>

bool detectCapitalUse(char* word) {
    int len = strlen(word);
    int caps = 0;

    for (int i = 0; i < len; i++) {
        if (isupper((unsigned char)word[i])) {
            caps++;
        }
    }

    if (caps == len || caps == 0) {
        return true;
    }

    return caps == 1 && isupper((unsigned char)word[0]);
}
