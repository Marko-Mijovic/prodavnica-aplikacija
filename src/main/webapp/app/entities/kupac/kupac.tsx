import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './kupac.reducer';
import { IKupac } from 'app/shared/model/kupac.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IKupacProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Kupac extends React.Component<IKupacProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { kupacList, match } = this.props;
    return (
      <div>
        <h2 id="kupac-heading">
          Kupacs
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Kupac
          </Link>
        </h2>
        <div className="table-responsive">
          {kupacList && kupacList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Naziv Kupca</th>
                  <th>Adresa</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {kupacList.map((kupac, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${kupac.id}`} color="link" size="sm">
                        {kupac.id}
                      </Button>
                    </td>
                    <td>{kupac.nazivKupca}</td>
                    <td>{kupac.adresa ? <Link to={`adresa/${kupac.adresa.id}`}>{kupac.adresa.id}</Link> : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${kupac.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${kupac.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${kupac.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Kupacs found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ kupac }: IRootState) => ({
  kupacList: kupac.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Kupac);
