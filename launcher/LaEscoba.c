/*
 * Lanzador de La Escoba: ejecuta exec_LaEscoba.jar (en la misma carpeta que
 * el .exe) con el Java instalado, en la consola actual.
 * Compilar: gcc -O2 -s -o LaEscoba.exe launcher/LaEscoba.c
 */
#include <windows.h>
#include <stdio.h>
#include <wchar.h>

int wmain(void)
{
    wchar_t dir[MAX_PATH], cmd[MAX_PATH * 2];
    DWORD n = GetModuleFileNameW(NULL, dir, MAX_PATH);
    if (n == 0 || n >= MAX_PATH) return 1;
    wchar_t *slash = wcsrchr(dir, L'\\');
    if (slash) *slash = L'\0';

    /* ranking.txt se lee/escribe con ruta relativa: trabajar en la carpeta del exe */
    SetCurrentDirectoryW(dir);
    /* Para que las tildes y la ñ se vean bien en la consola */
    SetConsoleOutputCP(CP_UTF8);

    _snwprintf(cmd, MAX_PATH * 2, L"java -Dstdout.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -jar \"%ls\\exec_LaEscoba.jar\"", dir);

    STARTUPINFOW si = { sizeof si };
    PROCESS_INFORMATION pi;
    DWORD code = 1;
    if (CreateProcessW(NULL, cmd, NULL, NULL, FALSE, 0, NULL, NULL, &si, &pi)) {
        WaitForSingleObject(pi.hProcess, INFINITE);
        GetExitCodeProcess(pi.hProcess, &code);
        CloseHandle(pi.hProcess);
        CloseHandle(pi.hThread);
    } else {
        printf("No se pudo ejecutar Java. Instala Java (15 o superior) y comprueba que 'java' esta en el PATH.\n");
    }

    printf("\nPulsa Intro para salir...");
    getchar();
    return (int)code;
}
