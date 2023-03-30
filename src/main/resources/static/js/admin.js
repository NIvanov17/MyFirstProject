let loadAllUsers = document.getElementById("loadAllUsers")

loadAllUsers.addEventListener('click', loadUsers)

function loadUsers() {

    let usersTable = document.getElementById('userTableBody');
    usersTable.innerHTML = '';

    fetch("http://localhost:8080/admin/all-users")
        .then(response => response.json())
        .then(json => json.forEach(user => {

            let row = document.createElement('tr');

            let idCol = document.createElement('th');
            idCol.textContent = user.id;

            let usernameCol = document.createElement('td')
            usernameCol.textContent = user.username;

            let resultRoles = '';
            let currentRoles = document.createElement('td');
            for(let role in user.roles){
                    resultRoles = resultRoles + role + " ";
            }
            // user.roles.forEach(r => resultRoles = resultRoles + r.toString() + " ")

            currentRoles.textContent = resultRoles;

            let rolesCol = document.createElement('td');
            let changeRolesBtn = document.createElement('a')
            changeRolesBtn.classList.add("btn");
            changeRolesBtn.classList.add("btn-dark");
            changeRolesBtn.innerText = "Change Roles";
            changeRolesBtn.href = `/admin/change/${user.id}`;

            rolesCol.appendChild(changeRolesBtn);

            row.appendChild(idCol);
            row.appendChild(usernameCol);
            row.appendChild(currentRoles);
            row.appendChild(rolesCol);

            usersTable.appendChild(row);

        }))
}