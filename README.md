Не так давно я задеплоил своё приложение на <b>облачный хостинг</b>, вы сможете открыть его перейдя по ссылкам:</i><br>
    <ul>
      <li><a href="http://tracker-kadukov.ru/">http://tracker-kadukov.ru/</a>  (может возникнуть предупреждение что соединение не безопасно, это происходит из-за того, что соединение происходит по http, а не https - в таком случае просто нажимайте кнопку "перейти в любом случае" или переходите по ссылке снизу). <b>ОЗНАКОМИТЬСЯ С ФУНКЦИОНАЛОМ САЙТА</b> можно <a href="https://github.com/NikitaKadukov/Task_Tracker_System">по ссылке</a> </li>
      <li><a href="https://tracker-kadukov.online/">https://tracker-kadukov.online/</a>  (предупреждения не возникает, так как протокол https)</li>
      <li><a href="http://tracker-kadukov.eu-central-1.elasticbeanstalk.com/">Запасной вариант</a>  (если предыдущие 2 ссылки не работают, то переходите по этой - тут домен от хостинга с длинным адресом)</li>
    </ul>
    <hr>
    
                                        Task-Tracker System(СИСТЕМА ОТСЛЕЖИВАНИЯ ЗАДАЧ)
                                        
Эта программа является моим Pet-проектом. В данном файле я расскажу о том, какая функциональность реализована в этом проекте, какие были использованы технологии и как можно запустить её на вашем ПК. Связаться со мной вы можете через Телеграмм @KvernPr или, написав на почту nikitaets2016@gmail.com.


                                              РЕАЛИЗОВАННАЯ ФУНКЦИОНАЛЬНОСТЬ
                                              
1. На НАЧАЛЬНОЙ СТРАНИЦЕ есть возможность совершить вход в систему, зарегистрировать новый аккаунт или восстановить пароль на уже имеющемся. Помимо этого, при попытке получения доступа к контенту таск-трекера в незарегистрированном состоянии будет происходить переадресация именно на эту веб-страницу.
 ![image](https://github.com/HiKvern/Task_Tracker_System/assets/86073191/380dd05d-d66e-4b1e-b035-545968ba8cc7)





2. На ВХОДЕ проверяется соответствие введёных логина и пароля тем данным, которые хранятся в базе данных. В случае несоответствия будет произведена переадресация на прошлую страницу. В случая соответствия - на главную страницу системы отслеживания задач.
 ![image](https://github.com/HiKvern/Task_Tracker_System/assets/86073191/7f862ae5-a784-4885-bfb5-6338a8c40634)





3. На странице РЕГИСТРАЦИИ присутствует валидация данных, которая будет выдавать ошибки введенных данных в случае, если будут введены некорректные данные со стороны пользователя.
 ![image](https://github.com/HiKvern/Task_Tracker_System/assets/86073191/9f0b7ff9-6ade-4c83-95d9-5758d5454e87)





4. Присутствует возможность ВОССТАНОВЛЕНИЯ ДАННЫХ. Для этого требуется ввод почтового адреса в поле формы. После произведённых манипуляций на введенную почту поступит письмо с данными пользователя, который указывал данную почту при регистрации. Если возникнет ошибка при отправке данных или пользователь укажет неверную электронную почту, то тогда на сайте выведется ошибка с конкретным сообщением.
 ![image](https://github.com/HiKvern/Task_Tracker_System/assets/86073191/3cd43fff-2ece-473a-a743-7a2dbd003266)





5. При авторизации на сайте происходит переадресация на ГЛАВНУЮ СТРАНИЦУ ТАСК-ТРЭКЕРА. На главной странице отображаются все активные задания на данный момент. Пользователь может не только просматривать уже имеющиеся задания, но и:
   
           * Добавлять новые задания
   
           * Добавлять новые подзадания (отличаются отсутствием дедлайна)
   
           * Изменять задания
   
           * Удалять задания

           * Помечать задания как выполненные

           * Сортировать задания по разным полям
 ![image](https://github.com/HiKvern/Task_Tracker_System/assets/86073191/d681b796-b6c1-4fe9-9a1c-f57c1a29466b)




![image](https://github.com/HiKvern/Task_Tracker_System/assets/86073191/af3dce0a-f153-409a-8d9b-7d03de1caa6f)



(Добавление задачи - при изменении задачи возникает подобное окно, но уже с вбитыми исходными данными, а при добавлении подзадачи не возникает поле с дедлайном задачи)
![image](https://github.com/HiKvern/Task_Tracker_System/assets/86073191/20832ee1-eeb3-4b88-a929-c2fab1b331cd)


(Удаление задачи, при нажатии на кнопку "Удалить" сайт просит подтверждение действия, так как это опасное действие)


 На этой же странице есть таблица с уже выполненными заданиями
  ![image](https://github.com/HiKvern/Task_Tracker_System/assets/86073191/1531ff7e-1db1-4a70-ad2e-8b8e65e84f96)


  
Их можно вернуть обратно в выполнение или же удалить. В зависимости от срока до дедлайна задания - оно может принимать различные стилевые характеристики и иметь различную анимацию. 





6. В ПРОФИЛЕ на сайте можно посмотреть данные о себе - основную личную информацию профиля. Помимо этого там есть возможность посмотреть статистику выполненных заданий и подзадач.
 ![image](https://github.com/HiKvern/Task_Tracker_System/assets/86073191/4ae19817-e490-4e6f-9164-3965452e9325)





7. Также в профиле реализована функциональность по СМЕНЕ ПАРОЛЯ или ПОЧТЫ
 ![image](https://github.com/HiKvern/Task_Tracker_System/assets/86073191/ccdcc071-f8ba-42d9-a7cf-918e6970a52d)




 При указании корректных данных произойдет смена этих данных
 ![image](https://github.com/HiKvern/Task_Tracker_System/assets/86073191/b0849859-6fb4-4287-a4fc-74b551e03b76)




 Если же данные не пройдут валидацию, то в прежнем окне с формой для ввода данных возникнет предупреждение красного цвета






 8. На сайте есть 3 роли пользователей - admin (один на весь сайт), subAdmin (их назначает admin) и user (обычные пользователи). Для 1-х и 2-х в профиле есть кнопка для перехода на СТРАНИЦУ АДМИНИСТРАЦИИ САЙТА. На это странице можно просмотреть всех пользователей, их регистрационные данные, а также при желании заблокировать им доступ на сайт. admin также может назначить любого user`a subAdmin`ом. subAdmin`ы такой функциональсти не имеют
![image](https://github.com/HiKvern/Task_Tracker_System/assets/86073191/f6e6ff38-d1f0-464e-b838-22a16658ac04)






9. Есть возможность настроить ТЕМНУЮ ТЕМУ приложения,  которая будет выглядеть подобным образом
 ![image](https://github.com/HiKvern/Task_Tracker_System/assets/86073191/3b5aca05-0033-433a-9541-4c4a2a6b6d40)






10. Есть раздел О САЙТЕ, на котором отображена некоторая информация о моем проекте и моих достижениях
![image](https://github.com/HiKvern/Task_Tracker_System/assets/86073191/4ad587e8-d50b-405b-bd92-b0f0aed1c941)






11. В нафигации предусмотрена возможность выхода из аккаунта. Помимо этого, при переходе на несуществующую страницу будет возникать СТРАНИЦА С ОШИБКОЙ 404
 ![image](https://github.com/HiKvern/Task_Tracker_System/assets/86073191/61eb3b29-403f-4753-8869-6e849f416a89)






12. В приложении реализовано также REST-взаимодействие. Сверху - запрос к приложению, снизу - ответ на него в json-формате
 ![image](https://github.com/HiKvern/Task_Tracker_System/assets/86073191/dded907c-3d76-46c7-a090-67929370d79c)

                                              ИСПОЛЬЗУЕМЫЕ ТЕХНОЛОГИИ

    В проекте были использованы следующие технологии:

    * Spring (Core, Boot, AOP, REST)
   
    * Hibernate
   
    * MySQL
   
    * ThymeLeaf

    * библиотека JAVAMAILSENDER
      
                                              КАК ЗАПУСТИТЬ НА ПК

    На данный момент запустить можно только скачав весь проект и запустив его в среде разработки, поддерживаемой Spring Boot 3.2.0. Также потребуется настроить базу данных, добавив таблицы с полями, соответствующими полям классов, которые вы найдете в пакете Entity. Также вам потребуется совершить дополнительные настройки для корректной работы отправки писем на почту.
