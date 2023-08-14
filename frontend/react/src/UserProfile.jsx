const UserProfile = ({name, age, gender,autoNumber, ...props}) => { // instead of using props we can directly declare variables as follows ({name,age,gender})

    gender = gender === "MALE"? "men" : "women";

    return (
        <div>
        <p>
            <strong>
                <h1>Name : {name}</h1>
                <p>Gender : {gender}</p>
                <p>Age : {age}</p>
            </strong>
        </p>

            <img src={`https://randomuser.me/api/portraits/${gender}/${autoNumber}.jpg`}/>

            {props.children}
        </div>
    )
}

export default UserProfile;

// ...props // this is for children and the rest of the props
// and then add-in {props.children}

