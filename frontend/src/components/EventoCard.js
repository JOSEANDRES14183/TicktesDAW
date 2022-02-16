import {Card, CardBody, CardText, CardTitle} from "reactstrap";

function EventoCard(props){

    return(
        <Card>
            <CardBody>
              <CardTitle tag={"h5"}>
                  {props.evento.titulo}
              </CardTitle>
              <CardText>
                  {props.evento.descripcion}
              </CardText>
            </CardBody>
        </Card>
    )
}

export default EventoCard;