char * defangIPaddr(char * address){
    int len = strlen(address);
    // An IPv4 address has 3 '.', which expand from 1 char to 3 chars ("[.]") -> +6 chars
    char *res = (char *)malloc((len + 7) * sizeof(char));
    int j = 0;

    for (int i = 0; i < len; i++) {
        if (address[i] == '.') {
            res[j++] = '[';
            res[j++] = '.';
            res[j++] = ']';
        } else {
            res[j++] = address[i];
        }
    }

    res[j] = '\0';
    return res;
}