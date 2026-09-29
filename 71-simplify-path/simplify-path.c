#include <stdlib.h>
#include <string.h>

char* simplifyPath(char* path) {
    int len = strlen(path);
    char** stack = (char**)malloc((len + 1) * sizeof(char*));
    int top = 0;

    char* token = strtok(path, "/");
    while (token != NULL) {
        if (strcmp(token, ".") == 0) {
            // Ignore current directory
        } else if (strcmp(token, "..") == 0) {
            if (top > 0) {
                top--;
            }
        } else {
            stack[top++] = token;
        }
        token = strtok(NULL, "/");
    }

    char* result = (char*)malloc(len + 2);
    result[0] = '\0';

    if (top == 0) {
        strcpy(result, "/");
    } else {
        for (int i = 0; i < top; i++) {
            strcat(result, "/");
            strcat(result, stack[i]);
        }
    }

    free(stack);
    return result;
}