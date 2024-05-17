import React from "react";
import { connect } from "react-redux";

function License(props){
    return (
        <div>
                <div>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla ornare, nisi in
                    placerat egestas, nisl turpis consequat lorem, at congue tortor mauris eget sapien. 
                    Praesent suscipit felis sit amet facilisis accumsan. Pellentesque eget mollis ipsum. Nullam lobortis 
                    elementum blandit. Pellentesque pharetra sem neque, blandit sodales nibh condimentum vel. Aliquam lorem lacus, 
                    vehicula et ornare at, convallis et libero. Duis quis lacinia eros.
                </div>
            <input type="checkbox" onClick={props.onCheckBox} id = "checkBox"/>
            {props.lock &&
                <button type="submit" id="button">Subscribe</button>
            }
        </div>
    );
}


function mapStateToProps(state){
    return {
        lock: state.lock
    }
}

function mapDispatchToProps(dispatch){
    return {
        onCheckBox: () =>{
            console.log('click');
            let action = {type: ''}
            if(document.querySelector('#checkBox').checked){
                action = {type: 'Lock'};
                console.log(action);
                dispatch(action);
            }else{
                action = {type: 'Unlock'};
                console.log(action);
                dispatch(action);
            }
        }
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(License);