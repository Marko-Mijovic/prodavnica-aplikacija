import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './artikal.reducer';
import { IArtikal } from 'app/shared/model/artikal.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IArtikalProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Artikal extends React.Component<IArtikalProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { artikalList, match } = this.props;
    return (
      <div>
        <h2 id="artikal-heading">
          Artikals
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Artikal
          </Link>
        </h2>
        <div className="table-responsive">
          {artikalList && artikalList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Naziv Artikla</th>
                  <th>Cena</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {artikalList.map((artikal, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${artikal.id}`} color="link" size="sm">
                        {artikal.id}
                      </Button>
                    </td>
                    <td>{artikal.nazivArtikla}</td>
                    <td>{artikal.cena}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${artikal.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${artikal.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${artikal.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Artikals found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ artikal }: IRootState) => ({
  artikalList: artikal.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Artikal);
