#### 1.3. Управляющие конструкции: if, switch, циклы. Работа с отладчиком
##### Задача №2 - Разная комиссия

В прошлый раз мы рассматривали упрощённый вариант вычисления комиссии. Пришла пора сделать нормальный.

Q: Почему?

A: Потому что так дешевле пользователям: за MasterCard и Maestro вообще не нужно платить пока не превысили лимит (замечание от 300 убираем), а для VK Pay всегда бесплатно:

[Условия](https://github.com/netology-code/kt-homeworks/blob/master/03_control/pic/vk-commission.png)

Напишите алгоритм расчёта в виде функции, передавая в функцию:

1. Тип карты/счёта (по умолчанию - Vk Pay)
1. Сумму предыдущих переводов в этом месяце (по умолчанию - 0)
1. Сумму совершаемого перевода
Функция по-прежнему должна возвращать комиссию в копейках.

Итого: у вас должен быть репозиторий на GitHub, в котором расположен ваш Gradle-проект.