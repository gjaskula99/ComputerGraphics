# Computer Graphics Project

Projekt z grafiki komputerowej.
Przedstawia wygenerowaną proceduralnie mapę o rozmiarze 800x800. Całość jest pokryta teksturami w oparciu o blendmapę, elewacja terenu wyliczona na podstawie rastrowej mapy wysokości. Mapa pokryta obiektami, których współrzędne generowane są pseudolosowo i ładowane z plików OBJ. Na całość nałożony skybox. Po mapie porusza się model gracza sterowany przez WSAD i spację, regulacja odległości kamery możliwa poprzez kółko myszy.

![Przechwytywanie](https://user-images.githubusercontent.com/81091594/189534903-6e21b0bc-77ec-4f81-b7ad-b7e1be22d9ce.PNG)

## Backend

Projekt napisany w Javie z wykorzystaniem biblioteki Light-weight Java Game Library (https://www.lwjgl.org).
Do uruchomienia w IDE niezbędne załączenie bibliotek zewnętrznych - umieszczone w /lib/jars oraz /lib/natives pod architekturę arm64. "Main" stanowi klasa EngineTester.
W przypadku Eclipsa:
1. Zaimportowanie projektu z kodu źródłowego.
2. Dodanie plików .jar LWJGL do projektu.

![JARs](https://user-images.githubusercontent.com/81091594/189535457-97d840ea-4109-4683-8b59-75971be5cfc9.PNG)

3. Wskazanie ścieżki natives w ustawieniach projektu.

![natives](https://user-images.githubusercontent.com/81091594/189535789-8d64cbb4-c929-4d36-a602-149af133e958.PNG)

Testy prowadzono na:
- JRE 8 na Windows 10 (amd64)
- JRE 17.0.3 na macOS 12.5 (arm64)
