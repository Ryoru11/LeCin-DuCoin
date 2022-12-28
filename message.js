
class Messages extends React.Component {
constructor(props) {
		super(props);

	}
    render() {
        if (!this.props.messages) {
            return <div>No Messages yet...</div>
        }

        return (
        <div>
              {this.props.messages.map((message,i) => {
                      if(message.messageType=="Question"){
                               return(QuestionMessage(message))

                      }
                      else{
                            return   (AnswerMessage(message))
                      }
                      })
                  }


         </div>
        );
    }
}

function QuestionMessage(props){
  return (
<div id="questionMessage">
    <table >
        <tbody>
        <tr>
            <td>
                <div className="media w-50 mb-3">
                    <img src="https://res.cloudinary.com/mhmd/image/upload/v1564960395/avatar_usae7z.svg" alt="user" width="50" className="rounded-circle">
                    </img>
                    <div className="media-body ml-3">
                        <div className="bg-light rounded py-2 px-3 mb-2">
                            <p className="text-small mb-0 text-muted"> {props.messageText}
                            </p>


                            <form action = "/deleteMessage" method = "GET" class="bg-light" >

                                <div class = "input-group">
                                   <input name="id" value ={props.id} hidden ></input>
                                    <button id = "button-remove"  class= "btn btn-link" > 
                                    coucou
                                        <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" fill="currentColor" class="bi bi-send mr-1 mt-1 pb-1" viewBox="0 0 16 16">

                                            <path d= "M 1232 2243 c -10 -16 -41 -71 -69 -123 c -28 -52 -92 -167 -143 -255 c -50 -88 -141 -258 -201 -378 c -61 -121 -112 -217 -113 -215 c -2 1 -112 199 -246 438 c -292 524 -287 515 -325 515 c -23 0 -31 -5 -33 -22 c -4 -28 -3 -31 294 -560 c 140 -248 254 -460 254 -470 c 0 -11 -25 -60 -56 -109 c -124 -195 -225 -367 -309 -524 c -48 -91 -112 -209 -142 -262 c -62 -111 -66 -141 -16 -146 c 30 -3 34 1 85 90 c 30 51 94 170 143 263 c 94 182 179 328 286 493 c 66 102 68 103 82 80 c 19 -30 197 -346 378 -671 c 110 -198 149 -259 166 -263 c 26 -7 53 11 53 34 c 0 9 -121 232 -269 496 c -148 265 -274 491 -280 503 c -10 20 3 51 104 252 c 63 126 158 305 211 398 c 53 92 128 228 167 301 c 56 108 67 135 57 147 c -20 24 -57 18 -78 -12 Z"/>

                                        </svg>    
                                    </button>
                                </div>
                            </form>


                        </div>
                        <p className="small text-muted">{props.messageDate}</p>
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>

  );
}
function AnswerMessage(props){
  return (
<div id="answerMessage">
    <table >
        <tbody>
        <tr>
            <td> </td>
            <td>
                <div className="media w-50  ml-auto mb-3">
                    <div className="media-body">
                        <div className="bg-primary rounded py-2 px-3 mb-2">
                            <p className="text-small mb-0 text-white">{props.messageText}
                            </p>
                        </div>
                        <p className="small text-muted">{props.messageDate}</p>
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>

  )
}

class MessageArea extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            messages: []
        }
    }
    componentDidMount() {
        fetch("/MessageList")
            .then(res => res.json())
            .then(
                (response) => {
                    this.setState({
                        messages: response
                    });
                },
                (error) => {
                    alert(error);
                }
            )
    }
    render() {
        return (
            <div id="main">
                <Messages messages={this.state.messages}/>
   </div>
        );
    }
}

ReactDOM.render(
    <MessageArea />,
    document.getElementById('messageArea')
);

