require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        a: Начнём. Напиши "help" для помощи
        script:
            $session.date = {}
            $session.year = {}


    state: Help
        q!: help
        a: 1) "назвать дату" - заполнение слота даты
        a: 2) "печать" - вывод значения даты
        a: 3) "год" - вывод значения года из даты
        
    state: Приветствие
        intent!: /привет
        a: Привет привет
        
    state: Дата
        intent!: /дата
        a: вы назвали дату {{ toPrettyString($parseTree._date) }}
        script: 
            $session.date = $parseTree._date.value;
            $session.year = $parseTree._date.year;
            $jsapi.log("--------дата: " + toPrettyString($parseTree) + " --------");
            $jsapi.log("--------дата(_date.value): " + toPrettyString($parseTree._date.value) + " --------");
            $jsapi.log("--------дата(_date.year): " + toPrettyString($parseTree._date.year) + " --------");

    state: Печать
        q!: печать
        a: дата: 
        a: {{ $session.date }}
        
    state: Год
        q!: год
        a: год:
        a: {{ $session.year }}

    state: Прощание
        intent!: /пока
        a: Пока пока

    state: Fallback
        event!: noMatch
        a: Я не понял. Вы сказали: {{$request.query}}

