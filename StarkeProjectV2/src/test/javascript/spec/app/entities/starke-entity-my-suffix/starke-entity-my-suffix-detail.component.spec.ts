/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Starkev2TestModule } from '../../../test.module';
import { StarkeEntityMySuffixDetailComponent } from 'app/entities/starke-entity-my-suffix/starke-entity-my-suffix-detail.component';
import { StarkeEntityMySuffix } from 'app/shared/model/starke-entity-my-suffix.model';

describe('Component Tests', () => {
    describe('StarkeEntityMySuffix Management Detail Component', () => {
        let comp: StarkeEntityMySuffixDetailComponent;
        let fixture: ComponentFixture<StarkeEntityMySuffixDetailComponent>;
        const route = ({ data: of({ starkeEntity: new StarkeEntityMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [StarkeEntityMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(StarkeEntityMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StarkeEntityMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.starkeEntity).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
