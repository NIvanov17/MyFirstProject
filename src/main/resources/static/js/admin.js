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
            let addRoleBtn = document.createElement('a')
            addRoleBtn.classList.add("btn");
            addRoleBtn.classList.add("btn-dark");
            addRoleBtn.innerText = "Add Role";
            addRoleBtn.href = `/admin/addRole/${user.id}`;

                let rolesCols = document.createElement('td');
                let removeRoleBtn = document.createElement('a')
                removeRoleBtn.classList.add("btn");
                removeRoleBtn.classList.add("btn-dark");
                removeRoleBtn.innerText = "Remove Role";
                removeRoleBtn.href = `/admin/removeRole/${user.id}`;

            rolesCol.appendChild(addRoleBtn);
            rolesCol.appendChild(removeRoleBtn);

            row.appendChild(idCol);
            row.appendChild(usernameCol);
            row.appendChild(currentRoles);
            row.appendChild(rolesCol);

            usersTable.appendChild(row);

        }))

        // async function onAddRoleBtn(event) {
        //         let username = event.currentTarget.getAttribute('id');
        //
        //         window.location.replace(`http://localhost:8080/admin/addRole/{id}`);
        //
        // }
}